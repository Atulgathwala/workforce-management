### 1. Link to your Public GitHub Repository
https://github.com/Atulgathwala/workforce-management

### 2. Link to your Video Demonstration
https://drive.google.com/file/d/19sWl3QCXNiEB8FpIBi8tIo_8jnb0n5tE/view?usp=drivesdk

### 3. Summary of Work

1. Spring Boot project setup using Gradle 
2. Project structured with proper folders and packages (controller, service, repository, dto, model)
3. In-memory storage implemented using Java collections 
4. Bug Fix 1: Task reassignment cancels previous task instead of creating duplicates 
5. Bug Fix 2: Cancelled tasks are excluded when fetching by date 
6. Feature 1: Smart daily task view returns active tasks within date range and active 
    tasks before range that are still pending 
7. Feature 2: Task priority added (HIGH, MEDIUM, LOW), with update and fetch endpoints 
8. Feature 3: Comment and activity log functionality added
    - POST /task-mgmt/{id}/comment adds a new comment and logs the activity
    - GET /task-mgmt/{id} includes all comments and activity logs sorted by timestamp

### 4. Known Issues
reassignment cancels previous task instead of creating duplicates

### 5. Notes
All features tested using Postman.
Task creation, reassignment, priority update,
and comment history working as expected. The project is ready for review. 
Thank you for the opportunity!
