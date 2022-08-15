## Users
```json
// Empty Reading List
{
        "username" : "username",
        "password" : "password", 
        "readingList": []
}

// User with Reading List but empty books
{
        "username" : "username",
        "password" : "password", 
        "readingList" : [
            {
                "name" : "username's reading list",
                "books" : []
            }
        ]
}

{
        "username" : "username",
        "password" : "password", 
        "readingList" : [
            {
                "name" : "username's reading list",
                "books" : [
                    {
                        "title": "title",
                        "pages": 1,
                        "published": "2022-08-17",
                        "author" : {
                            "name": "author"
                        }
                    }
                ]
            }
        ]
}
```