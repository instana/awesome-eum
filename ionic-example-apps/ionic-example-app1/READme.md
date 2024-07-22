# Set up ionic app monitored by Instana JavaScript Agent

## Set up a basic ionic app
- `npm install -g @ionic/cli` to install commandline 
- `ionic start`
- Select No on Do you want to use app creation wizard
- Select the desired framework (Angular, React, Vue) and give a project name
- Choose any one of the app starting templates

## Run the app and instrument with Instana
- `cd app-dir`
- Run `ionic serve`
- Instrument the index.html file with weasel monitoring script

The application will run on port `8100`

## Enable backend tracing

- Install instana agent on host machine and ensure configuration is set to `enable tracing`.
- Set up a server in the host machine.
- cd app-dir 
- Run `ionic build --prod` to build the project
- Copy the directory `dist` to your server. 
- In the configuration of your server, add a path to the dist directory and designate a listening port. 
- The application will now run on the specified port and backend trace will be visible on Instana Dashboard. 
