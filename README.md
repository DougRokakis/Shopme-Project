# Shopme-Project
Java Project for Resume with following tech used to complete Management/Administrator section of project:


					Spring Data JPA
 - Hibernate is used as JPA implementation by default.
 - Repositories:
	- Extend CrudRepository, PagingAndSortingRepository
	- Used pre-defined methods: save(), findAll(), findById(), deleteById()
	- Used custom queries with @Query annotation.
	- Used @Modifying and @Transactional for Update queries.
	- Pagination: Pageable, Sort, Page, PageRequest
	- No need for concrete methods as Spring Data JPA will generate implementationclasses at runtime
 - Spring Data JPA handles all details like connection & transaction -> let developers 
focus on business logics.
- Entities:
	- Code entity classes first -> create tables (foward engineering)
	- Use @Transient for getter methods that do not relate to field mapping

============================================================================================

					Spring Security
- Use password encoder (BCryptPasswordEncoder) to encode user's password.
- BCrypt is a secured, strong and adaptive cryptography algorithm.

============================================================================================

					RESTful Webservices
- Implement a simple RESTful webservice using @RestController (UserRestController).
- When used internally (no public APIs): no need to follow best practices (e.g. a REST method returns 
a String - not JSON).

============================================================================================

					Bootstrap, CSS and HTML
- Bootstrap provides great templates for UI components: navigation, menu item, modal dialog, table, form fields, 
buttons, pagination...
- Flexible and responsive grid system: row, col-xx, col-sm...
- Space utilities: margins (m-, mt-, mr-, mb-...) and padding (p-)
- Font Awesome icons: easy to use(CSS) & great icon set.
- CSS media queries to target different screen sizes.
- HTML 5 defines standard attributes for form fields validation -> Almost no need to use Javascript

============================================================================================

					Thymeleaf
- Thymeleaf is a great template engine - far better than JSP and JSTL.
- Integrated very well with Spring MVC (form handling).
- Thymeleaf expressions can be embedded in Javascript code -> generate Javascript code dynamically.
- Thymelead fragments for reusable code snippets.
- th:each -> iterate a collection
- th: propertyName -> generate value for an HTML attribute (th:src, th:href, th:class, th:action...)

============================================================================================

					Javascript & jQuery
- Use jQuery's .post() method to consume a RESTful webservice (AJAX call)
- Use Javascript to check file size and show image thumbnail
- jQuery selectors:
	-Select an element by ID: $("#id")
	-Select elements by CSS class: $(".class-name")
- Set attribute of an element:
	- element.attr("name", "value")
-Handle event of an input:
	- input.on("event-name", function(e){})
  
  ============================================================================================
  
					Other API's
- Generate CSV file user SuperCSV library
- Generate Excel document using Apache POI library
- Generate PDF document using OpenPDF library

============================================================================================

					Coding Techniques
- Respect n-tier architecture:
	- Repository layer: entity classes and queries
	- Service layer: business/domain-specific logics
	- Controller layer: requests handling
	- View layer: UI code generation
- Be flexible: controller calling repository directly
- Refactor code whenever possible, avoid code duplication
- Short methods with meaningful name is preferred to long-line methods

============================================================================================

MODIFICATIONS TO BE MADE:

1. User authentication
2. User authorization
3. Category Management
4. Brand Management
5. Product Management
6. Listing Products for Customers
7. Settings
8. Customer Registration
9. Customer Management
10. Customer Authentication
11. Shopping Cart
12. Shipping Rates Management
13. Address Book
14. Order Managment
15. Checkout
16. Sales Report
17. Product Review
