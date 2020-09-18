# Duke

This project is part of a module for CS2103 Software Engineering in NUS.

It is a todo list that runs on javafx.

**Changelog:**  https://github.com/therizhao/ip/blob/master/CHANGELOG.md

## How to run the program?

1. Download the latest release
2. Execute it

## How are commits done?

- Commits follow [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/) specification
- An [Intellij IDE tool](https://plugins.jetbrains.com/plugin/9861-git-commit-template) is used to enforce the styling 

## How is the Changelog generated?

To generate the changelog, run the follow steps

1. Run `npm i` in cli (to download `standard-version` package)
2. Run `npm run release` in cli
    - Git tag of the new version will be auto-added
    - Changelog will be auto-updated
