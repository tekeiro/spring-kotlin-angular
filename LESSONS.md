
# Learnt Lessons

+ **If at startup of spring boot, it shutdowns immediately, its because there is a wrong line in build.gradle**

     >    You have to modify build.gralde and change Tomcat dependency from 'runtime' to 'compile'

+ **Configure in Angular project that AJAX calls to /api/ ** goes to backend Spring project.**
    1. Create a file named 'proxy.conf.json' in Angular project root with following content:
		```
		{
		  "/api/*": {
			    "target": "http://localhost:8086/",
			    "secure": false,
			    "logLevel": "debug"
		  }
		}
		```
	 Take care about Spring boot port.
    
	2. Modify 'package.json' file that contains commands to execute.
	    > Change 'scripts.start' from 'ng serve' to 'ng serve --proxy-config proxy.conf.json'
	    That tell him to start Angular server and initialize a proxy with json passed to redirect
	    AJAX calls to Backend server.  
	    The file to pass is the file created at step 1
	  
	  
+ **Java 8 Time API types support:.** 
	1. Add following dependencies to Spring project:
		```
		compile("com.fasterxml.jackson.module:jackson-module-parameter-names")
		compile("com.fasterxml.jackson.datatype:jackson-datatype-jdk8")
		compile("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
		```
	
	2. Its recommendable that in Angular project, add *moment* library to
	treat dates easily in JS/Typescript
	    
	    ```npm install --save moment```
	
        Then, in DTO class we can declare dates using Java 8 Time API 
        (LocalDateTime, LocalDate, ...)
        and we can tell Jackson that to deserialize we will use string from JSON indicating
        the date format with following annotation:
	
        ```
        @JsonFormat (
            pattern= "<formato fecha aceptado por DateTimeFormatter>", 
            shape = <aqui se le dice en que formato viene de Json : JsonFormat.Shape.* >
        )
        ```
	    
	    About shape attribute, for example if we receive date as a string from JSON (that
	    its the usual situation), we put in the annotation:
	    
	    ```
	        shape=JsonFormat.Shape.STRING
	    ```   
	   
+ **Do compatible bidirectional binding between Angular and a HTML input of type date or datetime-local.**
  
    Source: [Answer in StackOverflow](https://stackoverflow.com/questions/38175251/angular-2-date-input-not-binding-to-date-value)
    
    In HTML template:
    
    ```
        <input type="datetime-local" #myDate  [value]="model.dateField | date: 'yyyy-MM-dd\'T\'HH:mm'"
        		(input)="model.dateField = parseDate(myDate.value)"  ... />
    ```
    
    We won't use *ngModel*. We will mount manually bidirectional binding.
    To do that, we need to add a parse date function in the Component. An example:
    
    ```
        parseDate(text: string): Date {
            return moment(text, "YYYY-MM-DD'T'HH:mm").toDate();
        }
    ```
    
	   
+ **Add global CSS styles and Javascript scripts** 
    
    We will add that scripts and CSS file paths in file 'angular-cli.json' in sections
    named *'styles'* and *'scripts'* respectively.
	
	   
		
+ **Distribute Angular application within Backend project**

    Change in the angular-cli.json file, in *'outDir'* section, you have to change
    value to a relative path folder that its contained into ```src/main/resources/static``` 
    route in Backend project.
    
    > **Note:** Not use static as folder to put angular app because angular will overwrite
    all content of that folder, deleting all subfolders.
    
    Example:
    
    ```
    {
      ...
      "outDir": "../spring-angular-backend/src/main/resources/static",
      ...
    }
    ```
	   