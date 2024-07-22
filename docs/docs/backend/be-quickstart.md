# 🔖 Quick Start Guide


### Steps to quick start server up and running

- 1. Download the `jar` file from [here](https://)
- 2. Make your 8081 port free
    - 1. run ```lsof -i :8081 | grep LISTEN | awk '{print $2}' | xargs kill -9``` 
- 3. Make sure you have JAVA_HOME setuped and having java version >=17
- 4. From the downloded `jar` file folder run the below command
    - ```java -jar YOUR_DOWNLOADED_JAR_FILE_NAME.jar```


After the above steps, try loading below url in the browser

[http://localhost:8081/v2/product/all](http://localhost:8081/v2/product/all)

If you could see the response, you are all set for utilising/running applicatins with these APIs