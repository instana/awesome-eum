# 🔖 Quick Start Guide


### Steps to quick start server up and running

- 1. Download/Clone repo from <a href="https://github.com/instana/awesome-eum/tree/master/backend-springboot-robotshops" target="_blank">here</a>
- 2. Open the application in Intellij IDE and sync libraries
- 3. Make your 8081 port free (or you can change the port number <a href="https://github.com/instana/awesome-eum/blob/master/backend-springboot-robotshop/src/main/resources/application.properties" target="_blank">here</a>.
    - 1. run ```lsof -i :8081 | grep LISTEN | awk '{print $2}' | xargs kill -9``` 
- 3. Make sure you have JAVA_HOME setup and having java version >=17
- 4. Click the run icon on IDE


After the above steps, try loading below url in the browser

<a href="http://localhost:8081/v2/product/all" target="_blank">http://localhost:8081/v2/product/all</a> 

If you could see the response, you are all set for utilising/running applicatins with these APIs