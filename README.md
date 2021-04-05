# Project 1.5

## Instructions

1. clone the repository and change the working directory to inside the root folder
> git clone https://github.com/RevatureRobert/Project1.5-template.git
>
> cd Project1.5-template

2. Install the archetype onto your local repository
> mvn install

3. Move into a directory where you want your project to be located.

4. Generate the new project
> mvn archetype:generate \
> -DarchetypeArtifactId="Project1.5" \
> -DarchetypeGroupId="dev.enterprise" \
> -DgroupId=< your group id > \
> -DartifactId=< your project id >

5. Your project should be created and have all the necessary files to begin your new project!

## Specs
- Remove the JDBC logic and refactor with Hibernate ORM.
- Add in servlets and host on Tomcat Server.
- Deploy to AWS using Elastic Beanstalk (optional)



## Classes

### ReimbursementHandlerServlet
* RController
* ReimbursementDAO
### UserHandlerServlet
* UController
* UserDAO
### getByLoginServlet
* UserDAO




## GET (Select)
```python
http://localhost:8081/HibernateProject/getAll.R 
http://localhost:8081/HibernateProject/getAll.User

http://localhost:8081/HibernateProject/byAuthor.R
{
    "author": 1
}
http://localhost:8081/HibernateProject/byUsername.User
{
    "username": "dan3"
}
```
## Post (Create)
```python
http://localhost:8081/HibernateProject/rServlet.R
{
    "id" : 5,
    "amount" : 300.06,
    "submitted" : "2021-11-04 11:11:10",
    "resolved" : "2020-09-071 18:21:21",
    "description" : "Long",
    "author" : 2,
    "resolver" : 3,
    "status_id" : 6,
    "type_id" : 8
}
http://localhost:8081/HibernateProject/user1.User
{
    "userid":1,
    "username": "asasw",
    "password": "qdscefvsdvrssdvsdff",
    "firstname": "Mark",
    "lastname": "Riots",
    "email": "asas@gmail.com",
    "roleid": 103
}
```
## Del (Delete)
```python
http://localhost:8081/HibernateProject/rServlet.R
{
    "id": 5
}
http://localhost:8081/HibernateProject/user1.R
{
    "username": "asasw"
}
```
## Put (Update)
```python
http://localhost:8081/HibernateProject/rServlet.R
{
    "id" : 5,
    "amount" : 409.06,
    "submitted" : "2021-11-04 11:11:10",
    "resolved" : "2020-09-071 18:21:21",
    "description" : "Long",
    "author" : 2,
    "resolver" : 4,
    "status_id" : 1,
    "type_id" : 8
}
```