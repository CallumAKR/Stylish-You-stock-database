package com.drisq.util;


import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * Application JAR location utility class. An instance of this class enables the
 * location of the applications's main jar file to be determined, so long as it
 * exists, and has been loaded from the file-system.
 * </p>
 * <p>
 * This class also provides some utility operations for Jar files located by
 * this class, such as being able to get the Jar's main attributes (@link
 * {@link #getJarFileMainAttributes(File)}
 * </p>
 * <p>
 * Note there is precisely one instance of this class, which can be obtained by
 * invoking the {@link #getInstance()} method.
 * </p>
 *
 * @author Anthony Smith
 */
public class AppJarLocUtil {

  private URL jarApplicationLocation;

  private static Pattern JAR_LOC_PATTERN = Pattern.compile("^jar:([^!]*)!.*$");

  private AppJarLocUtil(Class<?> cls) throws IOException {
    URL classUrl = cls.getResource('/' + cls.getName().replace('.', '/') + ".class");
    String urlString = classUrl.toExternalForm();
    Matcher matcher = JAR_LOC_PATTERN.matcher(urlString);

    if (!matcher.matches()) {
      throw new IOException("Cannot locate application jar file for class " + cls.getName());
    }
    jarApplicationLocation = new URL(matcher.group(1));
  }

  /**
   * Gets the location of the jar that contains this class.
   *
   * @return the unique instance of this class.
   * @throws IOException when the Jar that contains this class cannot be
   *           located.
   */
  public static AppJarLocUtil getInstance() throws IOException {
    return new AppJarLocUtil(AppJarLocUtil.class);
  }

  /**
   * Gets the location of the jar that contains the provided class.
   *
   * @param cls a class of the jar that we wish to locate.
   * @return the an instance of this class.
   * @throws IOException when the Jar that contains this class cannot be
   *           located.
   */
  public static AppJarLocUtil getInstance(Class<?> cls) throws IOException {
    return new AppJarLocUtil(cls);
  }

  /**
   * Gets the URL that the JAR was loaded from.
   *
   * @return the URL that the JAR was loaded from.
   */
  public URL getLocation() {
    return jarApplicationLocation;
  }

  /**
   * Gets the file that the JAR was loaded from.
   *
   * @return the file that the JAR was loaded from, or {@literal null} if the
   *         JAR was not loaded from a file.
   */
  public File getFileLocation() {
    String path;
    try {
      path = jarApplicationLocation.toURI().getPath();
    } catch (URISyntaxException e) {
      path = jarApplicationLocation.getPath();
    }
    return path == null || "".equals(path) ? null : new File(path);
  }

  /**
   * A utility method for getting the jar-file's manifest.
   *
   * @param jarFile the jar-file to examine.
   * @return the jar-file's manifest.
   * @throws IOException when there is a problem accessing the jar-file.
   */
  public static Manifest getJarFileManifest(File jarFile) throws IOException {
    JarFile jar = new JarFile(jarFile);

    Manifest manifest = jar.getManifest();

    jar.close();

    return manifest;
  }

  /**
   * <p>
   * A utility method for getting the main attributes of a jar-file's manifest.
   * This is equivalent to the following command:
   * </p>
   * <p>
   * {@code getJarFileManifest(jarFile)..getMainAttributes()}
   * </p>
   *
   * @param jarFile the jar-file to examine.
   * @return the main attributes of the provided jar-file's manifest.
   * @throws IOException when there is a problem accessing the jar-file.
   */
  public static Attributes getJarFileMainAttributes(File jarFile) throws IOException {
    return getJarFileManifest(jarFile).getMainAttributes();
  }

}
