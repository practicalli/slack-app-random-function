# practicalli/random-function-slack-app

```none
██████╗ ██████╗  █████╗  ██████╗████████╗██╗ ██████╗ █████╗ ██╗     ██╗     ██╗
██╔══██╗██╔══██╗██╔══██╗██╔════╝╚══██╔══╝██║██╔════╝██╔══██╗██║     ██║     ██║
██████╔╝██████╔╝███████║██║        ██║   ██║██║     ███████║██║     ██║     ██║
██╔═══╝ ██╔══██╗██╔══██║██║        ██║   ██║██║     ██╔══██║██║     ██║     ██║
██║     ██║  ██║██║  ██║╚██████╗   ██║   ██║╚██████╗██║  ██║███████╗███████╗██║
╚═╝     ╚═╝  ╚═╝╚═╝  ╚═╝ ╚═════╝   ╚═╝   ╚═╝ ╚═════╝╚═╝  ╚═╝╚══════╝╚══════╝╚═╝
```

## Project Status

TODO: add status badges for <https://github.com/Practicalli/{{scm/repo>}} workflows and issues

A Slack App to post a random function from the Clojure standard library.

- `/clojure-function` posts a message to slack with a random function

Project created with [deps-new](https://github.com/seancorfield/deps-new) and the [practicalli/application template](https://github.com/practicalli/project-templates)


## Slack setup

Create a Slack account and follow the prompts to create a new workspace, which will be used to develop the Slack App.

[Slack Quickstart](https://api.slack.com/start/quickstart) describes how to create a Slack app.

[Create a new Slack app with the Slack UI](https://api.slack.com/apps)

Select **From Scratch**

Enter App Name and select the Development Workspace to experiment and build the app. 

> Regardless of development workspace, the app can be distributed to any other workspaces.

### Configure scopes

Add a scope to post messages to a channel

**Sidebar** > **OAuth & Permissions** > **Scopes** > **Add an OAuth Scope**

- `chat:write` scope to the Bot Token to allow the app to post messages
- `channels:read` scope too so your app can gain knowledge about public Slack channels

> Reisntall the app if changing scopes and other features


### Update Display information

> This feels like it should have been done before installing the app

Provide a short & long description of the app and set background colour.  Optionally add an app icon (between 512 and 2000 px in size).

Short description

A random function from the Clojure Standard Library

Long description

A random function is selected from the Clojure Standard Library and displayed along with the documentation (doc string) to explain what the function does.

A bonus feature will be to provide examples of function use.


### Install app into development workspace
Install your app to your Slack workspace to test it and generate the tokens you need to interact with the Slack API. You will be asked to authorize this app after clicking an install option.

Sidebar > Settings > Basic Information > Install your app


### Authorization token

The Authorization token for the workspace is in the [app management web page](https://api.slack.com/apps/) for the specific app

**Sidebar** > **OAuth & Permissions** > **OAuth Tokens for Your Workspace**

Create an environment variable to hold the authorization token with the value of the **Bot User OAuth Token**

```shell
export SLACK_AUTHENTICATION_TOKEN=xxxx-123412341234-12345123451245-...   
```

> Environment variables used with Clojure must be set before running the REPL, so the variables are available to the Java Virtual Machine process.
>
> Add the environment variables to `.bashrc` for Bash or `.zshenv` for Zsh


Access tokens represent the permissions delegated to the app by the installing user. 

> Avoid checking access tokens into version control 



## Test app  - save for later...

Add the app to a public channel and test its (as yet unconfigured slash command)




## Slack background info

#### Scopes overview

Scopes give the app permission to carry out actions, e.g. post messages, in the development workspace.

Open the development workspace, either in a web page or in the Slack desktop app.

**Sidebar** > **OAuth & Permissions** > **Scopes** > **Add an OAuth Scope**

- `chat:write` scope to the Bot Token to allow the app to post messages
- `channels:read` scope too so your app can gain knowledge about public Slack channels
- `commands` scope to build a Slash command. 
- `incoming-webhook` scope to use Incoming Webhooks. 
- `chat:write.public` scope to gain the ability to post in all public channels, without joining. Otherwise, you'll need to use conversations.join, or have your app invited by a user into a channel, before you can post.
- `chat:write.customize` scope to adjust the app's message authorship to make use of the username, icon_url, and icon_emoji parameters in `chat.postMessage`.


> Add scopes to the Bot Token. 
> 
> Only add scopes to the User Token when the app needs to act as a specific user (e.g. post message as user, set user status, etc.)


### Slack API Methods

Your access token allows you to call the methods described by the scopes you requested during installation.

For example, your chat:write scope now allows your app to post messages. Your app probably isn't a member of any channels yet, so pick a channel you don't mind adding some test messages to and /invite your app.

You can find the corresponding id for the channel that your app just joined by looking through the results of the conversations.list method:

```shell
curl https://slack.com/api/conversations.list -H "Authorization: Bearer xoxb-1234..."
```

You'll receive a list of conversation objects.

Now, post a message to the same channel your app just joined with the chat.postMessage method:

```shell
curl -X POST -F channel=C1234 -F text="Reminder: we've got a softball game tonight!" https://slack.com/api/chat.postMessage -H "Authorization: Bearer xoxb-1234..."
```

Voila! We're already well on our way to putting a full-fledged Slack app on the table.

[Web API Guide](https://api.slack.com/web)

[API Methods list](https://api.slack.com/methods)

[Interactive Workflows](https://api.slack.com/interactivity)


## Local Development

Use Socket Mode to route the app interactions and events over a WebSockets connection instead sending payloads to Request URLs, the public HTTP endpoints.

Socket mode is intended for internal apps that are in development or need to be deployed behind a firewall. It is not intended for widely distributed apps. 

Alternatively, use ngrock to redirect requests to the local app.


### Install app into workspace

Install Slack app into a workspace (not the development workspace)

**Sidebar** > **Install App** > **Install App To Workspace** > **Slack OAuth UI**


## Run the application

Run the application (clojure.main)

```shell
clojure -M:run/app
```

Run the greet function (clojure.exec), optionally passing a `:name` key and value as arguments

```shell
clojure -X:run/greet :team-name '"team name"'
```

## Development

Practicalli workflow overview:

- start a REPL process in a Terminal
- open the project in a Clojure Editor and connected to the REPL
- write code and evaluate expressions in the editor using the source code files

[Practicalli Clojure CLI Config](https://practical.li/clojure/clojure-cli/practicalli-config/) should be used with this project to support all aliases used.

This project uses `make` tasks to run the Clojure tests, kaocha test runner and package the service into an uberjar.  The `Makefile` uses `clojure` commands and arguments which can be used directly if not using `make`.

`make` command in a terminal will list all the tasks available

```shell
make
```


### Run Clojure REPL

Start the REPL with the [Practicalli REPL Reloaded](https://practical.li/clojure/clojure-cli/repl-reloaded/) aliases to include the custom `user` namespace (`dev/user.clj`) which provides additional tools for development (Portal data inspector, hotload libraries, namespace reload)

```shell
make repl
```

The local nREPL server port will be printed, along with a help menu showing the REPL Reloaded tools available.

Evaluate the practicalli.random-function-slack-app namespace and a mulog publisher will start, sending pretty printed events to the console. Evaluate `(mulog-publisher)` to stop the mulog publisher.

Call the `-main` function with or without an argument, or call the `greet` function directly passing an optional key and value pair.

`(namespace/refresh)` will reload any changed namespaces in the Clojure project.


### Clojure Editor

If a REPL has been run from a terminal, use the editor **connect*- feature.

Otherwise, use the `:dev/reloaded` alias from Practicalli Clojure CLI Config to starting a REPL process from within a Clojure editor.


### Unit tests

Run unit tests of the service using the kaocha test runner

```shell
make test
```

> If additional libraries are required to support tests, add them to the `:test/env` alias definition in `deps.edn`

`make test-watch` will run tests on file save, stopping the current test run on the first failing test.  Tests will continue to be watched until `Ctrl-c` is pressed.

## Format Code

Check the code format before pushing commits to a shared repository, using cljstyle to check the Clojure format, MegaLinter to check format of all other files and kaocha test runner to test the Clojure code.

Before running the `pre-commit-check`

- [install cljstyle](https://github.com/greglook/cljstyle/releases){target=_blank}
- MegaLinter runs in a Docker container, so ensure Docker is running

```shell
make pre-commit-check
```

Run cljstyle only

- `make format-check` runs cljstyle and and prints a report if there are errors
- `make format-fix` updates all files if there are errors (stage, stash or commit work  forman and check format changes via `git diff`)

Run MegaLinter only

- `make lint` runs all configured linters in `.github/config/megalinter.yaml`
- `make lint-fix` as above and applies fixes

Run Kaocha test runner only

- `make test` runs all unit tests in the project, stopping at first failing test
- `make test-watch` detect file changes and run all unit tests in the project, stopping at first failing test


## Deployment

Build an uberjar to deploy the service as a jar file

```shell
make build-uberjar
```

- `make build-config` displays the tools.build configuration
- `make build-clean` deletes the build assets (`target` directory)

```shell
make docker-build
```

- `make docker-down` shuts down all services started with `docker-build`
- `make docker-build-clean`

Or build and run the service via the multi-stage `Dockerfile` configuration as part of a CI workflow.


## License

Copyright © 2023 Practicalli

[Creative Commons Attribution Share-Alike 4.0 International](http://creativecommons.org/licenses/by-sa/4.0/")
