USAGE
-----
run "yarn start" to start the server
run "yarn all" to build and watch the source code


- yarn is used as package manager
- browserify-server is used to host the files 
- browserify is used to bundle the app
- watchify is used to watch for changes and deploy them
- babelify is used to transform es6 code to es5
- babel-preset-es2015 is used to babelify es6 code
- babel-preset-react is used to babelify react code


## Proxy for yarn
yarn config set proxy http://

yarn config set https-proxy http:// 