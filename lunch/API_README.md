# API Documentation

## RestaurantController

### Description
The `RestaurantController` is responsible for handling the submission of restaurant information. It communicates with a WebSocket service to broadcast the submitted restaurant details to all users within the session.

### Endpoints

#### `POST /submit`
- **Description**: Submits restaurant information provided by a user and publishes it to all users within the session.
- **Request Payload**:
  ```json
  {
    "uuid": "string", 
    "restaurantName": "string", 
    "user": "string"
  }
  ```
- **Flow**:
  1. Receives the restaurant information payload.
  2. Calls `restaurantService.submitRestaurant(restaurantInfo)` to process the submission.
  3. Uses `webSocketService.submitSession(restaurantInfo)` to broadcast the restaurant information to all users within the session.
- **Exception Flow**:
  1. **Duplicate Submission**: Ensures that a user does not submit the same restaurant more than once.
  2. **Duplicate Restaurant**: Prevents the same restaurant from being added multiple times for fairness.

---

## SessionController

### Description
The `SessionController` manages sessions, including creating, ending, and retrieving session information. It also handles real-time updates via the WebSocket service.

### Endpoints

#### `POST /session/create`
- **Description**: Creates a new session.
- **Response**:
  ```json
  {
    "uuid": "string",
    "user": "string"
  }
  ```
- **Flow**:
  1. Calls `sessionService.createSession()` to start a new session.
  2. Constructs a `SessionInfo` object from the created session data.
  3. Uses `webSocketService.connect(sessionInfo)` to establish WebSocket communication for the session.
  4. Returns the created `SessionInfo` object, containing the session's UUID and the initiating user's information.

#### `POST /session/end`
- **Description**: Ends an existing session by the initiator.
- **Request Body**:
  ```json
  {
    "uuid": "string",
    "user": "string"
  }
  ```
- **Flow**:
  1. Calls `restaurantService.pickRandomRestaurant(info.getUuid())` to randomly select a restaurant from the session's submissions.
  2. Uses `webSocketService.publishSelection(info.getUuid(), resultInfo)` to broadcast the selected restaurant to all users within the session.
  3. Calls `sessionService.endSession(info.getUuid(), info.getUser())` to terminate the session.
- **Exception Flow**:
  1. **No Session Found**: User is unable to end a session that has already ended or does not exist.

#### `GET /session/connect/{uuid}`
- **Description**: Retrieves session information by UUID.
- **Path Variable**:
  - `uuid`: The UUID of the session provided by the initiator.
- **Response**:
  ```json
  {
    "uuid": "string",
    "user": "string",
    "restaurantInfos": "string[]"
  }
  ```
- **Flow**:
  1. Calls `sessionInfoService.getSession(uuid)` to retrieve the session information and the list of restaurants submitted during the session.
  2. Uses `webSocketService.connect(session)` to establish WebSocket communication for real-time updates.
  3. Returns the retrieved `SessionInfo` object, containing the session's UUID, the initiating user's information, and the list of restaurant submissions.
- **Exception Flow**:
  1. **No Session Found**: User is unable to join a session that has already ended or does not exist.

---
