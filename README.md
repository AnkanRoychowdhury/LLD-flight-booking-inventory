# Flight Inventory Management System - Low Level Design

## 📋 Problem Statement

Design an application for **flight inventory and booking management systems** where:

- Every day **multiple flights** are flying on different sectors
- Flights can have **multiple fares** and for each fare there will be a **list of seats**
- The system should handle real-time seat availability and booking operations
- Users should be able to search, view, and book flights with proper fund management

---

## 🔧 System Assumptions

| Assumption | Description |
|------------|-------------|
| **Data Source** | There is a supplier that is providing flight data |
| **Fare Structure** | Flights can have multiple fare types and for each fare type airline is providing an available seat list |
| **Seat Exclusivity** | If a seat is already booked, other users can't book the same seat |
| **Date Reference** | Take today's date as **1** |
| **Date Format** | For departDate simple number is provided like - 1, 2, 3, 4, etc |
| **Time Format** | Assume 24 hour format and will be a decimal number |
| **User Funds** | User fund will be a decimal number only |

---

## 🚀 Core Features

### 1. User Management

#### `AddUser(userId, name, funds)`

**Purpose**: Register a new user in the system with initial wallet balance

**Input Parameters**:
- `userId` (String): Unique identifier for the user
- `name` (String): Full name of the user
- `funds` (Double): Initial wallet balance

**Output Format**:
```
<userId, name, funds>
```

**Example**:
```
Input: AddUser("u1", "Vinit", 5000)
Output: <u1, Vinit, 5000>
```

---

### 2. Flight Search

#### `SearchFlight(from, to, departDate, paxCount)`

**Purpose**: Search for available flights matching the specified criteria

**Input Parameters**:
- `from` (String): Origin airport code
- `to` (String): Destination airport code
- `departDate` (Integer): Departure date (1, 2, 3, 4, etc.)
- `paxCount` (Integer): Number of passengers

**Business Logic**:
- Return flights for the same date
- Only return flights where available seats ≥ provided pax count

**Output Format**:
```
<flightNumber, airline, from, to, departDate, departTime, price, fareType, List<seat>>
```

**Example**:
```
Input: SearchFlight("DEL", "BLR", 2, 1)
Output: <123, 6E, DEL, BLR, 2, 10, 2000, F1, [1b, 2c, 4e]>
```

---

### 3. Flight Booking

#### `BookFlight(userId, from, to, flightNumber, departDate, fareType, List<seat>)`

**Purpose**: Book flight tickets for a user with specified seats

**Input Parameters**:
- `userId` (String): User identifier
- `from` (String): Origin airport code
- `to` (String): Destination airport code
- `flightNumber` (String): Flight number to book
- `departDate` (Integer): Departure date
- `fareType` (String): Fare category (F1, F2, etc.)
- `List<seat>` (List): Specific seats to book

**Business Rules**:
- ✅ All seats must be available
- ✅ All seats must belong to the same fareType
- ✅ User must have enough funds in wallet
- ✅ Deduct funds from user account for booking
- ✅ Mark seats as booked (unavailable for others)

**Output**:
- **Success**: Return `bookingId`
- **Failure**: Return proper error message

**Example**:
```
Input: BookFlight("u1", "DEL", "BOM", "111", 2, "F1", ["10a", "11c", "20b"])
Output: "BOOKING_12345" or "Error: Insufficient funds"
```

---

### 4. Advanced Search (Bonus Feature)

#### `SearchFlightByPreferredAirline(from, to, departDate, paxCount, preferredAirline, sortBy, sortType)`

**Purpose**: Enhanced flight search with airline preference and sorting options

**Additional Parameters**:
- `preferredAirline` (String): Preferred airline code (6E, SJ, UK, AI, QP)
- `sortBy` (String): Sort criteria (PRICE, TIME, etc.)
- `sortType` (String): Sort order (ASC, DESC)

**Output Format**:
```
<flightNumber, airline, from, to, departDate, departTime, price, List<seat>>
```

---

## 🧪 Test Cases & Commands

### User Registration
```bash
ADDUSER u1 Vinit 5000
ADDUSER u2 Neha 1500
```

**Expected Output**:
```
<u1, Vinit, 5000>
<u2, Neha, 1500>
```

### Flight Search Operations
```bash
SEARCHFLIGHT DEL BLR 2 1
SEARCHFLIGHT DEL BLR 2 2  
SEARCHFLIGHT DEL HYD 2 2
```

### Flight Booking Operations
```bash
BOOK u1 DEL BOM 111 6e 2 F1 10a 11c 20b
BOOK u1 DEL BOM 211 6e 2 F2 10a 11c 20b  
BOOK u2 DEL BOM 141 6e 2 F4 32e
```

### Advanced Search with Preferences
```bash
SEARCHFLIGHT DEL BLR 2 1 AI PRICE DESC
```

---

## 📊 Flight Data Repository

The system is pre-loaded with the following flight inventory:

| Flight No. | Airline | From | To | Available Seats | Departure Date | Departure Time | Arrival Time | Price | Fare Id | Seats Available |
|------------|---------|------|----|-----------------|-----------------|-----------------|--------------|---------|---------|--------------------|
| 123 | 6E | DEL | BLR | 3 | 2 | 10 | 11 | 2000 | F1 | 1b, 2c, 4e |
| 156 | 6E | DEL | BLR | 1 | 2 | 14 | 15 | 4000 | F2 | 4e |
| 234 | 6E | DEL | HYD | 4 | 2 | 15 | 16 | 1000 | F3 | 29a, 40e, 1e, 4e |
| 456 | 6E | AMD | CCU | 10 | 2 | 18 | 22 | 10000 | F5 | 7c, 7d, 12c, 5f, 8e, 7e, 4d, 3e, 4a, 10a |
| 987 | SJ | DEL | BLR | 6 | 2 | 11 | 16 | 2500 | F11 | 12c, 5f, 4d, 3e, 4a, 10a |
| 1001 | SJ | DEL | HYD | 5 | 2 | 9 | 12 | 2300 | F1 | 8e, 7e, 4d, 6a, 15a |
| 890 | SJ | DEL | AMD | 7 | 2 | 12 | 18 | 2100 | F2 | 45e, 30a, 1e, 4e, 7c, 7d, 12c |
| 999 | SJ | DEL | HYD | 12 | 2 | 8 | 12 | 2900 | F1d | 1e, 4e, 7c, 7d, 12c, 5f, 8e, 7e, 4d, 3e, 4a, 10a |
| 432 | UK | DEL | BLR | 8 | 2 | 16 | 18 | 2000 | F1x | 5a, 10b, 34e, 20c, 20a, 8b, 8a, 9b |
| 444 | UK | BLR | DEL | 2 | 2 | 11 | 13 | 4300 | F1d | 12a, 13b |
| 456 | UK | DEL | BLR | 7 | 2 | 19 | 21 | 2800 | F1 | 5a, 10b, 34e, 20c, 3e, 4a, 10a |
| 654 | AI | DEL | BLR | 4 | 2 | 21 | 23 | 3600 | F1 | 41a, 44b, 44c, 12d |
| 236 | AI | DEL | BLR | 10 | 2 | 17 | 19 | 6700 | F1 | 20c, 20a, 8b, 8a, 9b, 10c, 21a, 18b, 18a, 19b |
| 980 | QP | DEL | BLR | 4 | 2 | 13 | 14 | 1500 | F1 | 1a, 1b, 1c, 1d |
| 875 | QP | DEL | BLR | 4 | 2 | 10 | 12 | 2800 | F1 | 4a, 1b, 11c, 21d |
| 989 | QP | DEL | BLR | 4 | 2 | 19 | 21 | 3400 | F1 | 34a, 41c, 11c, 7d |
| 998 | QP | DEL | BLR | 4 | 2 | 18 | 22 | 4500 | F1 | 34a, 41c, 11c, 7d |
| 123 | 6E | DEL | BLR | 2 | 2 | 10 | 11 | 2100 | F11 | 3b, 12c |
| 156 | 6E | DEL | BLR | 1 | 2 | 14 | 15 | 4300 | F21 | 41e |
| 234 | 6E | DEL | HYD | 4 | 2 | 15 | 16 | 2000 | Fx3 | 28a, 12e, 14e, 14e |
| 456 | 6E | AMD | CCU | 5 | 2 | 18 | 22 | 4000 | F5x | 17c, 17d, 15c, 51f, 81e |

### Airline Codes Reference
- **6E**: IndiGo
- **SJ**: SpiceJet
- **UK**: Vistara
- **AI**: Air India
- **QP**: Qatar Airways

### Popular Routes Covered
- **DEL ↔ BLR**: Delhi to Bangalore (Most flights)
- **DEL → HYD**: Delhi to Hyderabad
- **DEL → AMD**: Delhi to Ahmedabad
- **AMD → CCU**: Ahmedabad to Kolkata
- **BLR → DEL**: Bangalore to Delhi

---

## 📋 Implementation Guidelines

### ❌ Restrictions
- **No Authentication**: Do not implement user/admin authentication
- **No Database**: Do not use any database or NoSQL store
- **No UI**: Do not create any UI for the application

### ✅ Allowed Approaches
- **Input Methods**: Read from file, STDIN, or coded in driver method
- **Output Methods**: Write to file or STDOUT
- **Storage**: Use in-memory data structures only
- **Language**: Free to use language of your choice

---

## 🎯 Code Quality Expectations

### Functional Requirements
- **Demo-ready**: Code should be demo-able and functionally complete
- **Error Handling**: Code should fail gracefully with proper error messages for corner/invalid cases
- **Exception Management**: Use exceptions for handling error cases
- **Modular Design**: Code should be modular, think in terms of Object-Oriented Design

### Input/Output Handling
- **Flexible Input**: Input can be taken from command line or in the main function
- **Memory Storage**: Use in-memory data structure for all operations
- **Driver Coverage**: Driver class should cover all scenarios

### Optional Enhancements
- **Unit Testing**: Implement unit tests (recommended)
- **Logging**: Add comprehensive logging
- **Performance**: Consider performance optimizations for large datasets

---

## 🚀 Implementation Roadmap

### Phase 1: Core Setup
1. **Define Data Models**: User, Flight, Fare, Booking entities
2. **Setup In-Memory Storage**: HashMap-based repositories
3. **Load Sample Data**: Initialize with provided flight data
4. **Basic CRUD Operations**: User management basics

### Phase 2: Core Features
1. **User Management**: Implement AddUser functionality
2. **Flight Search**: Implement basic SearchFlight
3. **Booking System**: Implement BookFlight with validations
4. **Error Handling**: Add comprehensive exception handling

### Phase 3: Advanced Features
1. **Advanced Search**: Implement SearchFlightByPreferredAirline
2. **Sorting Logic**: Add price/time-based sorting
3. **Enhanced Validations**: Edge case handling
4. **Performance Optimization**: Indexing for faster searches

### Phase 4: Testing & Polish
1. **Unit Tests**: Comprehensive test coverage
2. **Integration Testing**: End-to-end test scenarios
3. **Error Scenarios**: Test all failure paths
4. **Code Review**: Refactor and optimize

---

## 🧪 Edge Cases & Error Handling

### User Management Errors
- Duplicate user ID registration
- Invalid fund amounts (negative values)
- User not found during operations

### Flight Search Errors
- No flights available for route/date
- Invalid airport codes
- Invalid passenger count (0 or negative)

### Booking Errors
- Insufficient user funds
- Seats already booked by another user
- Seat and fare type mismatch
- Invalid flight number or date
- Booking seats from different fare categories

### System Errors
- Invalid input formats
- Memory constraints with large datasets
- Concurrent booking conflicts

---

## 📈 Success Metrics

### ✅ Must-Have Features
- [x] All three core APIs implemented and working
- [x] All provided test cases pass successfully
- [x] Proper error handling for edge cases
- [x] Clean, modular code structure
- [x] In-memory data persistence throughout session
- [x] Command-line interface functional

### 🎯 Nice-to-Have Features
- [x] Bonus advanced search functionality
- [x] Comprehensive unit test suite
- [x] Performance optimizations
- [x] Detailed logging and monitoring
- [x] Code documentation and comments

---

## 🔍 Sample Execution Flow

```bash
# Initialize users
> ADDUSER u1 Vinit 5000
Output: <u1, Vinit, 5000>

# Search flights  
> SEARCHFLIGHT DEL BLR 2 1
Output: <123, 6E, DEL, BLR, 2, 10, 2000, F1, [1b, 2c, 4e]>
        <987, SJ, DEL, BLR, 2, 11, 2500, F11, [12c, 5f, 4d, 3e, 4a, 10a]>

# Book flight
> BOOK u1 DEL BLR 123 2 F1 1b 2c
Output: BOOKING_001

# Verify user funds deducted
> GETUSER u1  
Output: <u1, Vinit, 1000> // 5000 - 4000 (2 seats * 2000)
```

---
## 📝 Conclusion
*This LLD problem tests my object-oriented design skills, business logic implementation, data structure usage, and error handling capabilities. Focus on creating a robust, maintainable solution that handles real-world booking scenarios effectively.*
<br>
*This is currently <b>In Progress</b>. All features will be added soon...*