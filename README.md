# My Templates JetBrains Plugin

## Overview
My Templates is a JetBrains IDE plugin designed to enhance your development workflow by allowing you to manage and apply custom templates to specific file types. With this plugin, you can easily define and manage templates for files with certain extensions, automatically applying predefined templates whenever a new file with that extension is created.

## Features
Template Management by Directory: Create templates within the /my_templates directory of your project, categorized by directories. These templates can then be used to generate new files based on the selected template.

## Usage

Creating a Custom Template

1. In your project, create a /my_templates directory at the root level.
2. Inside the /my_templates directory, create subdirectories for each template category you want to manage. 
3. Within each subdirectory, create your template files. 
4. When creating a new file, you can select the desired template from the /my_templates directory, and the plugin will automatically apply the content of the template to the new file.

5. Within your template files, you can use placeholders like {name} and {Name}. When creating a new file, the plugin will prompt you to enter a value, and it will replace {name} with the lowercase version of the entered value and {Name} with the capitalized version of the entered value.

#### Example:
- Template content: Hello, {Name}! Welcome to {name} world.
- User input: world
- Result: Hello, World! Welcome to world world.

### Using .myt Extension

- You can create files with the .myt extension in your project. These files will be recognized and treated as plain text files by the plugin.

#### Example:
- /my_templates/component/{name}.tsx.myt
- Template content: const {name} = () => {};
- User input: Badge
- Result: create Badge.tsx and const Badge = () => {}