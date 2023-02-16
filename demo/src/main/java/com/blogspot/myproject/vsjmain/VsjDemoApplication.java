package com.blogspot.myproject.vsjmain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@SpringBootApplication(scanBasePackages = {"com.blogspot.myproject"})
//@SpringBootApplication
@RestController

public class VsjDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(VsjDemoApplication.class, args);
        System.out.println("hello");
//        Student list[]={new Student(1,"A","address1"),new Student(2,"B","Address2"),
//                        new Student(3,"C","Address3"),new Student(3,"D","Address4"),
//                        new Student(5,"D","Address5")
//        };
    }

    Student list[]={new Student(0,"A","address0"),new Student(1,"B","Address1"),
            new Student(2,"C","Address2"),new Student(3,"D","Address3"),
            new Student(4,"E","Address4"),new Student(5,"F","Address5"),
            new Student(6,"G","Address6")
    };
    //**************************************************************************
    @GetMapping("/Amit")
    public String myName() {
        return "Amit";
    }


    //**************************************************************************
    @GetMapping("/")

    public String index() {
        return "Welcome";
    }

    @GetMapping("/test")

    public String doTest(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s", name);
    }

    @GetMapping("/sub")

    public String dosub(@RequestParam(value = "a", defaultValue = "0") int a, @RequestParam(value = "b", defaultValue = "0") int b) {
        return String.format("%s - %s = %s", a, b, a - b);
    }

    @GetMapping("/add")

    public String doAdd(@RequestParam(value = "a", defaultValue = "0") int a, @RequestParam(value = "b", defaultValue = "0") int b) {
        return String.format("%s -  %s = %s", a, b, a + b);
    }

    @GetMapping(value = {"/doadd", "/doadd/{a}", "/doadd/{a}/{b}"})


    public String getBook(@PathVariable Optional<Integer> a, @PathVariable Optional<Integer> b) {
        int x = 0, y = 0;
        if (a.isPresent())
            x = a.get();
        if (b.isPresent())
            y = b.get();
        return String.format("%s +  %s = %s", x, y, x + y);
    }

    @GetMapping("/pathparam/{a}/{b}")
    public @ResponseBody
    String pathparam(@PathVariable Optional<String> a, @PathVariable Optional<String> b) {
        if (!a.isPresent())
            a = Optional.of("0");
        if (!b.isPresent())
            b = Optional.of("1");

        return "Path " +  a.get() + ":" + b.get();
    }

    @GetMapping("/mypathparam/{a}/{b}")
    public @ResponseBody
    String mypathparam(@PathVariable Optional<String> a, @PathVariable Optional<String> b){
        if(!a.isPresent())
            a=Optional.of("0");
        if(!b.isPresent())
            b=Optional.of("1");
        return "path" + a.get() + ":" +b.get() + ":"+ (Integer.parseInt(a.get())+Integer.parseInt(b.get()));
    }

    @GetMapping("/book")
    public ResponseEntity<Book> book() {
        try {
            Book book = new Book("The Recursion Sutras", "Recursion", 299);

            return ResponseEntity.ok(book);

        } catch (Exception ex) {
            return ResponseEntity.notFound().build();//Return Not Found
        }
    }

    @GetMapping("/student")
    public ResponseEntity<Student> getStudent(@RequestParam(value = "rollno", defaultValue = "0") int rollno){
        try{
//            Student student=new Student(12,"amit","varanasi");
            return ResponseEntity.ok(list[rollno]);
        }
        catch(Exception ex){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/allstudent")
    public ResponseEntity<Student[]> getStudent(){
        try{
//            Student student=new Student(12,"amit","varanasi");
            return ResponseEntity.ok(list);
        }
        catch(Exception ex){
            return ResponseEntity.notFound().build();
        }
    }
}
