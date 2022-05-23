# Final Challenge TMDB

## Description

The Tests suite will execute two classes in order shown below:
  1. API tests Class
  2. UI test Class

Every tests class have three test cases:
  1. Create a list
  2. Add a movie to the list (This test case depends on the test *1. Create a list*)
  3. Delete a movie (This test case depends on the tests *1. Create a list and 2. Add a movie to the list*)

For UI testing, the browser can be changed modifying the variable *browserExplorer* in the beforeClass method.

* Automation should be executed from TestNG.xml file.  
![image](https://user-images.githubusercontent.com/71049324/169721342-f8801b5e-3579-4023-8a70-c4c067b9d185.png)

## Reports
### Failed Test cases
The report will show the execution log and the information of the failed tests cases (including screeshots for UI tests cases)
![image](https://user-images.githubusercontent.com/71049324/169721887-f370e7a9-13fa-4110-8f20-d62518de3f26.png)
![image](https://user-images.githubusercontent.com/71049324/169721957-ad93ad42-9e15-428b-aeab-05f6a00a19c7.png)

### Passed and Skipped Test cases
The report will show the execution log
![image](https://user-images.githubusercontent.com/71049324/169722034-9b720237-82c5-4e61-85c5-3a246cd8f589.png)
![image](https://user-images.githubusercontent.com/71049324/169722056-8ad52ebc-4de0-450a-98a5-709a075a0aa5.png)
