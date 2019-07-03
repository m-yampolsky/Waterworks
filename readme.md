# Minimum Technical Requirements:
- Java JRE 8+ Installed
- 4GB RAM
- 32MB Free Space

<br/>

# Execution Instrucitons:
- Extract .zip and open unzipped folder
- Double-click Waterworks.jar file

<br/>

# Troubleshooting:
- Press `Windows Key + R`
- Type `regedit` then hit Enter
- Navigate to the `Computer\HKEY_CLASSES_ROOT\jarfile\shell\open\command`
- Double-click the `(default)` key
- Find the lovation of your `javaw.exe` JRE file in a File Explorer window, and copy the path
  (e.g `"C:\Program Files\Java\jre1.8.0_201\bin\javaw.exe"`)
- Paste the path into the value of the `(default)` key in the Registry Editor surrounded by double quotes
- Add ` -jar "%1" %*` to the end of the key value
- Hit Enter and close the Registry Editor window and Open the Waterworks.jar file again
### *continue if the jar still fails to execute*
- Press `Shift + Right-click` in the folder containing the Waterworks.jar file
- Hit `Open PowerShell Window Here`
- Paste `java -jar Waterworks.jar` and hit Enter
- Enjoy.

<br/>

# Built With:
- Java JDK 1.8.0_201
- IntelliJ IDEA Community Edition
- Adobe Illustrator CC

<br/>

# Authors:
- Vansh Juneja
- Maria Yampolsky

<br/>

# Acknowledgments:
- Toby Fox, Vampire Weekend (Music)
- Envato.com (JavaFX Tutorials)
- Bob Cross (Stack Overflow Solution)
