# Instana Android Agent Inject in Libraries

Instana Android-agent can also use to monitor a library level app using this example. 

Instructions
------------

* **Step 1:** Clone the repository

* **Step 2:** Unzip the jar at [/libs/plugin.jar.zip](/libs/plugin.jar.zip), This is a jar that supports lib level injection.

* **Step 3:** Change the `URL` and `KEY` in [/ibmkony/src/main/java/com/ibm/ibmkony/InstanaIBM.kt](/ibmkony/src/main/java/com/ibm/ibmkony/InstanaIBM.kt)

* **Step 4:** Run the application.

Result
------------

You'll notice that `ibmkony`, the custom library where Instana is integrated, can monitor the application using the bridge provided at the library level. The only change in application level is applying teh plugin.