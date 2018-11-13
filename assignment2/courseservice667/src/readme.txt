ELB URL:
Studentinformationadminsystem-env.yjmypmnyth.us-east-2.elasticbeanstalk.com
github link:
https://github.com/balla2017/Cloud-computing

course:
get:
…/webapi/courses/all
…/webapi/courses/{coursesID}, e.g.: /webapi/courses/0001

delete:
…/webapi/courses/{coursesID},e.g.: //webapi/courses/0002

post:
…/webapi/courses/
body:
{
"boradId": "22",
"courseId": "0002",
"department": "INFO",
"professorId": "Andrew.Ben",
"boardId":"003",
"listOfRegisteredStudents": ["01","02","03"],
"taId": "apple.wu"
}

put:
…/webapi/courses/{coursesID},e.g.: /webapi/courses/0002
{
"boradId": "22",
"courseId": "0002",
"department": "INFO",
"professorId": "Andrew.Ben",
"boardId":"003",
"listOfRegisteredStudents": ["01","02","03"],
"taId": "a"
}

announcement:
get:
…/webapi/announcements/all
…/webapi/announcements/byannouncement/{announcementid}, e.g./webapi/announcements/byannouncement/222
…/webapi/announcements/byboad/{boardid}, e.g. /webapi/announcements/byboard/22

delete:
…/webapi/announcements/{announcementID}, e.g. /webapi/announcements/111

post:
…/webapi/announcements
body:
{
"boardId": "11",
"announcementId": "111",
"announcementText": "Class is ending."
}

put:
…/webapi/announcements/{announcementID}, e.g. /webapi/announcements/111
body:
{
"boardId": "11",
"announcementId": "111",
"announcementText": "Class is mid."
}

professor:
get:
…/webapi/professors
…/webapi/professors/{professorID}, e.g./webapi/professors/John.Smith

post:
…/webapi/professors
body:
{
"firstName": "Steven",
"lastName": "Sun",
"department":"Computer Science",
"joiningDate":"10/01/1949"
}

put:
…webapi/professors/{professorid}, e.g….webapi/professors/John.Smith
body:
{
"firstName": "Steven",
"lastName": "Sun",
"department":"Computer Science",
"joiningDate":"10/01/1949"
}

delete:
…webapi/professors/{professorid}, e.g….webapi/professors/John.Smith

board:
get:
…/webapi/boards/allboards
…/webapi/boards/board/{boardId}, e.g.…/webapi/boards/board/111
…/webapi/boards/course/{courseId}, e.g. …/webapi/boards/course/1111

post:
…/webapi/boards
body:
{
    "boardId": "333",
    "courseId": "3333"
}

put:
…/webapi/boards/{boardID}, e.g. …/webapi/boards/111
body:
{
    "boardId": "333",
    "courseId": "3333"
}

delete:
…/webapi/boards/{boardID}, e.g. …/webapi/boards/111

student：
get
…/webapi/students/allstudents
…/webapi/students/bydepartment/{department}, e.g. …/webapi/students/bydepartment/{INFO}
…/webapi/students/{studentID}, e.g. …/webapi/students/NUID201701

post:
…/webapi/students
body:
{
    "department": "CSYE",
    "firstName": "Wendy",
    "joiningDate": "10/01/1949",
    "lastName": "Deng",
    "registeredCourses": [
        "Big data",
        "Machine learning"
    ],
    "studentId": "NUID201712"
}

put:
…/webapi/students/{studentID}, e.g. …/webapi/students/NUID201712
{
    "department": "INFO",
    "firstName": "Wendy",
    "joiningDate": "10/01/1949",
    "lastName": "Deng",
    "registeredCourses": [
        "Big data",
        "Machine learning"
    ],
    "studentId": "NUID201712"
}

delete:
…/webapi/students/{studentID}, e.g. …/webapi/students/NUID201712
