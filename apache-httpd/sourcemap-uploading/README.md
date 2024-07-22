# Demo for sourcemap uploading

Step 1: Copy build folder to /root/images/ on your host

Step 2: Modify reportingUrl, key, src in index.html

Step 3: Run start.sh to launch apache httpd container

Step 4: Log on Instana UI, create sourcemap uploading configuration and upload the source map file

Step 5: Access http://<httpd-server>:3380/#/deals/create
  
Step 6: Check JavaScript stack trace can be translated in Instana UI.
  
