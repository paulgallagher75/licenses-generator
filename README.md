1. Set correct parent in pom.xml.
2. Paste dependencies which should be included in licenses.xml generation.
3. Execute `mvn clean package`
4. Verify that licenses.xml and licenses.html are correct (you might need to update templates or pom.xml and repeat step 4).
5. Archive the licenses `zip -r licenses.zip src/main/resources/licenses`.