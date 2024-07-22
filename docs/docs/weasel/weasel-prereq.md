# 🔖 Prerequesties for running sample apps from weasel

## System Requirements
- **Operating System**: Windows, macOS, or Linux
- **Processor**: Intel Core i3 or equivalent
- **RAM**: 4 GB minimum (8 GB recommended)
- **Storage**: 1 GB of available disk space for development tools and project dependencies

## Software Requirements
1. **Node.js**
   - Download and install the latest LTS version of Node.js from [Node.js official website](https://nodejs.org/).
   - Verify installation:
     ```bash
     node -v
     npm -v
     ```

2. **npm (Node Package Manager)**
   - Comes bundled with Node.js installation.
   - Verify installation:
     ```bash
     npm -v
     ```

3. **Code Editor/IDE**
   - Recommended: [Visual Studio Code](https://code.visualstudio.com/)
   - Other options: Atom, Sublime Text, WebStorm

## Tools and Libraries
1. **Version Control System**
   - Install Git from [Git official website](https://git-scm.com/).
   - Verify installation:
     ```bash
     git --version
     ```

2. **Package Managers**
   - **npm**: Comes with Node.js installation.
   - **yarn**: Alternative to npm. Install it via npm:
     ```bash
     npm install --global yarn
     ```

3. **Build Tools**
   - **Webpack**: A module bundler for JavaScript applications.
     ```bash
     npm install --save-dev webpack webpack-cli
     ```
   - **Babel**: A JavaScript compiler for using the latest JavaScript features.
     ```bash
     npm install --save-dev @babel/core @babel/preset-env @babel/cli
     ```

4. **Development Server**
   - **Live Server**: Extension for Visual Studio Code for live reloading.
   - **http-server**: A simple, zero-configuration command-line HTTP server.
     ```bash
     npm install --global http-server
     ```

5. **Linting and Formatting**
   - **ESLint**: A tool for identifying and fixing JavaScript code issues.
     ```bash
     npm install --save-dev eslint
     ```
   - **Prettier**: An opinionated code formatter.
     ```bash
     npm install --save-dev prettier
     ```

## Optional Tools
1. **Testing Frameworks**
   - **Jest**: A delightful JavaScript testing framework.
     ```bash
     npm install --save-dev jest
     ```
   - **Mocha**: A feature-rich JavaScript test framework running on Node.js.
     ```bash
     npm install --save-dev mocha
     ```

