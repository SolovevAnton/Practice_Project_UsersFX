# Practice_Project_UsersFX
Practice project to study JavaFX, UI creation.
Project completed in 10h

UI:

![ui_of_usersFX](https://github.com/SolovevAnton/Practice_Project_UsersFX/assets/121192850/c3df2321-2402-4a00-a946-ace9bf0733c6)

## General Tasks
### Basic UI creation
1. Create a ComboBox control on the form
2. Load data from the https://jsonplaceholder.typicode.com/users resource into the ComboBox by creating a download repository from the server.
3. By clicking on the “Select” button, add the selected User object to the ListView
4. After adding the selected object to the list, remove it from the ComboBox
5. Implement a button that will allow you to remove the selected object from the ListView

### Menu options
1. add Menu control with options: open, save and save as
2. The open option opens a file selection dialog box, the save as option opens a dialog box for saving to a file. In both dialog boxes, add filters for .json and txt formats and for any type of format
3. By clicking on the open option, read data into the repository from the specified file to the repository. Next, display the selected objects in the ComboBox
4. By clicking on the option to save a save the added objects in the ListView in .json format. Also in remember and use as default the user-selected path to the file
5. Upon clicking on save, implement the following mechanism: if the user has not previously saved data to a file or did not choose to save as, then call the method for the save as option. If he previously performed these actions, then saqve to the file

## New windoiws creation
1. In the UsersFX project, by selecting an item from the ListView and pressing the “Info” button, open a new UserInfoController window, with ifo about the user displayed in text fields EXCEPT object fields of the user
2. The main window must wait for the second one to close, and after closing, display a message in the main window that the viewing of the object is over
