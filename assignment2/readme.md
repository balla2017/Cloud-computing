assignment3 requirement:
In this assignment you will send notifications (using AWS SNS) to students using AWS Lambdas. 
Step 1 - Extend Students table - Every student should now have a emailId field. 
Step 2 - Extend Courses -  Every New Course now has a SNS topic. 
     You will also create a SNS topic to receive email notifications about the course.  
     Store the SNS topic detail in the course object like so - 
    
Course
     - Id (Dynamo Db generated) and hash key
     - courseId - DynamoDbIndexHashKey, a Global Secondary Index (GSI)
     - professorId
     - taId
     - department
     - boardId
     - listOfRegisteredStudents/roster - has student Id list (this is the roster)
     - notificationTopic // NEW FIELD for storing sns topic. 
Step 3 - Register Student for Course action.      
    You will create a post action for - the following URL student/{studentId}/register
     A student can register for a max of 3 courses.
     POST Request body should take in course details and add the courseIds mentioned in the body to the registeredCourses list.
     You will then access the courseIds sns topic, and subscribe the student’s emailId to that topic using SNS’s subscribe API.
      
Step 4 - New Announcement should result in email to all students 
A lambda should trigger for every new Announcement table object. (refer our tutorial on AWS Lambdas, look in the dynamo db stream section). 
The lambda will send a notification  with the announcement text to the sns topic associated with the courseId. 
