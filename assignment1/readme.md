StudentInformationSystem 
- The system manages Professors, Students and Programs.
- Every Program has Courses
- Every Course has Lectures, and each lecture will have notes, and associated material
- Every Course will have a board
- Every Course will have a roster 
- Every Course has enrolled Students
- Every Course has one associated Professor, and a Student TA
- Every Student has information in the system 
- Name        
- StudentId
- image     
- courses enrolled
- program name
ELB URL link: Studentinformationadminsystem-env.yjmypmnyth.us-east-2.elasticbeanstalk.com
-You can use like this:
URL: 
./webapi/classes/(courseID, e.g.: csye6225, info6205,cs5600)/students
./webapi/classes/(courseID, e.g.: csye6225, info6205,cs5600)/lectures
./webapi/classes/(courseID, e.g.: csye6225, info6205,cs5600)/professor
./webapi/classes/(courseID, e.g.: csye6225, info6205,cs5600)/studentTA
./webapi/professors/1
./webapi/lectures/id(e.g.100001-100008)
./webapi/programs/id(e.g.666)/students
./webapi/programs/id(e.g.666)/courses
./webapi/students/id(e.g.10001-10006)/courses
to find information you want to query.
