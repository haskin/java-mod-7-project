# Notes

## Relationships
- Books can have a unidiretional relationship with Authors
-users


- GET /api/books should return just book information, no author or genre

- GET /api/books/{id} should return book with the author

## Validation
### User
- All fields should be @NotNull
    - RequestUserDTO with both fields
    - ResponseUserDTO with only one username field
### ReadingList
- All fields @NotNull
### Author
- All fields @NotNull
### Book
- Author should @NonNull
- published should be a valid Date (@Temporal), else make it null. It allowed to be null (some books have no known published dates)
- Pages must have @Min(0) and default of 0 if empty
-  published is empty give it the default value of today