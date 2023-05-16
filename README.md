![](img/dashboard_banner.png)

# Workstation Dashboard
This Java-based project is a software system designed to serve as a central platform for a trading company for accessing and managing work-related information. The system provides secure login and account management functionality, and is capable of reading and writing data from text files. 

<p align="center">
 <a href="https://www.java.com/en/" target="_blank">
    <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java Badge" />
 </a>
 &nbsp;
 <a href="" target="_blank">
  <img src="https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=eclipse&logoColor=white" alt"Eclipse Badge" />
 </a>
</p>

---
---

## Introduction
 The _Workstation Dashboard_ is a Java-based program designed to serve as a centralized platform for managing and processing data related to trading activities. The program offers various features, such as handling multiple accounts, reading from text files, and pre-stored data, writing and appending new data, displaying information in table form and text fields, and more.

The program consists of two main parts:
1. **The Login Phase:** This is where users must provide their username and password to access the system. Once authenticated, the system stores and manages information respective to the signed-in account.
2. **The Main Terminal:** This is the window which serves as the landing page of the program. This landing page offers unique functions and features based on the access limitations of each individual user.

Before accessing the program, users must first login with their credentials. To facilitate this process, a couple of users and employees have been inserted into the `personelData.txt` file. The program includes multiple exception handling cases, from having the text fields empty to mismatching username and passwords or invalid inputs. After the initial login phase, users are directed to the main page based on their position in the company. This page is customized to offer specific features and functions depending on the user's access level.

Overall, the _Workstation Dashboard_ provides a secure and efficient way for businesses to manage and process trading-related data.

---

## Landing Page
The software has three different landing pages for the managers, sellers, and warehouse keepers respectively. Each landing page is customized to the user's role and job title, with options and access limitations specific to their position. For example, managers have access to almost all of the software's features, except for selling products directly. Warehouse keepers can add, change, or remove products from the warehouse. Each employee benefits from their own role while also having strict limitations on their ability to see or change data.

All members have access to the "File, Edit, Financial, Help" tabs, but their menu items will differ depending on their position. For example, managers can generate monthly reports of trading records, manage payments and salaries of their employees, and review vacation requests from their sellers and keepers. All three types of users can access the company's personnel and product lists. Note that only public information for each member will be shown to those without administrative access.

The "Exit" function is available to all users, and a pop-up system has been implemented to prevent users from accidentally closing windows. Under the "Edit" tab, all users can edit their personal information except for job ID, payment type, and payment value. Managers can edit these details for their sellers and keepers, but no two managers can manipulate each other's information or fire each other.

The "Financial" tab is used for tasks related to payments or business operations. Managers can view the company's monthly report and total earnings for the month, pay their employees' salaries, and manage each person's vacation request. Keepers and sellers can only submit vacation requests through this tab, and their requests are automated based on their position with limitations on the number of off-days. They will receive warnings for exceeding their allowed number of days during the submission phase.

Lastly, the “Help” tab, which shows the text below:
```
> How the 'personelData.txt' file is written: 
  position|username|firstName|lastName|password|personalID|jobID|paymentType|paymentValue|birthDate|education
...
```

---

## Personel & Product Lists
The option to access the list of the personel and the products in the warehouse is constructed as followed :
1. Firstly, a specific class will be made to use the OOP (object oriented programming) features, this will help later on with creating table views based on those specific types of data.
2. Secondly, the text file containing the required data will be read and its information will be stored in a 2D string array to work with.
3. Thirdly, the elements from the 2D array will be passed to their designated table columns. Based on their place, they will be shown in their respected column. Now this table will be set in a layout and then placed in a scene to be used in the stage. This way, there will be more options to work with the table or even manage the paddings of the window.

> Since there is no data manipulation in this instance, no changes will be added to our original files and their content will be safe and secure.

---

## Editing Information
Here, the user can manage their own profile details and change their `username`, `firstname`, `lastname`, `password` (which has a `confirm_new_password` function), `personal ID`, `birth date` and even their `education level` (or degree).

> Note that some fields are not editable and cannot be changed, for instance the `position`, `job ID`, `payment type` and `payment value`, which can only be changed by a manager. Regarding whether or not two managers can edit eachother’s personal info or not, it will be addrressed later on.

---

## Adding/Removing Employess
This feature is only allowed for *managers*, and even then, managers can only remove other sellers or keepers, and not eachother. Note that a new manager can be added to the company without a problem. In the adding phase, each and every sinlge field should be set by that manager in order to specify the new member correctly and prevent any possible short comings later on. Note that exception handling and errors have been managed here fully, from checking whether or not a field has been left empty or not, all the way to checking the length of the personal IDs or even checking if there are any non-suitable characters in a specific field, such as having a letter in the payment value field or others.

After insterting the new data, the new member’s info will be appended to the end of the `personelData.txt` file in order to prevent any data loss problems or re-writing issues. Now moving on to the removal feature, a table will be shown to select the employee that is about to get fired, then by clicking the remove button, that person will be removed fully, from the table and in fact even from the `personelData.txt` file.

---

## Adding/Removing Products

---

## Vacations

---

## Monthly Reports

---

## Salaries

---
---

# Conclusion
The development process of this program has been an excellent learning experience for me. As a developer, I have gained valuable experience working with Java and object-oriented programming (OOP) principles. I also got to work with JavaFX, which enabled me to create a sleek and user-friendly graphical user interface (GUI) for the software.

Although there were challenges along the way, such as working around bugs and fixing errors, the satisfaction of overcoming those challenges and seeing the final product made this project both enjoyable and rewarding. I am grateful for the opportunity to work on this project, and I believe it has helped me grow as a developer.

---
---

# License
This repository is licensed under the [MIT License](https://opensource.org/license/mit/).

<p align="center">
 <a href="https://lbesson.mit-license.org/" target="_blank">
   <img src="https://img.shields.io/badge/License-MIT-blue.svg" alt="MIT Badge" />
 </a>
</p>
