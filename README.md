## dbhelper

### stucture

1. Created specific classes for each entities, like User, Category, etc instead of lists of String for easier use

2. Select methods will convert Cursor to objects of these classes (multiple records will become a list) and return

3. Update methods also accept these objects. typically, update something is like: 
     * use select to get some objects
    * use setter to change their values, 
    * then pass the object to update methods as parameter

4. Delete just need id

### type

1. Money records use BigDecimal type now (avoid precision loss like float / double)

   *  When storing into db, BigDecimal is converted to long (long integer) in java, and then become INTEGER in db
   
   * in java code, money values are processed using BigDecimal methods, like 
       * *a = a + b* is like *a = a.add(b)*, 
       * *a = -a* is like *a = a.negate()*
       * *a < b* is like *a.compareTo(b) < 0*

2. Date records use LocalDate

   * When storing into db, they are converted to String, then stored as TEXT type

3. All convert methods are in util/Converter class
