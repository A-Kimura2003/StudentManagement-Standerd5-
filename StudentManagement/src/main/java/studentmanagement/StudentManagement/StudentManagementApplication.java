package studentmanagement.StudentManagement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.print.attribute.standard.PagesPerMinute;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class StudentManagementApplication {
//	private static String name = "noiz";
//	private static String age = "0";

	private static Map<String,String> student = new HashMap<>();

	private static String selectedStudentName = "";

	public static void main(String[] args) {
		//localhost:8080
		SpringApplication.run(StudentManagementApplication.class, args);

//		Map<String,String>student = new HashMap<>();
		student.put("Yanagi","28");
		student.put("Kasiwa","36");
		student.put("Momiji","13");

	//	for (Map.Entry<String,String> entry : student.entrySet()){
	//		name = entry.getKey();
	//		age = entry.getValue();
	//	}
	}

//	@GetMapping("/studentInfo")
//		return name + ":" + age;
//		return student;
//	}
	@GetMapping("/studentInfo")
	public String getStudentInfo(){
		if(selectedStudentName.isEmpty()){
			return "No student selected.";
		}
		return student.containsKey(selectedStudentName)
				? selectedStudentName + "：" + student.get(selectedStudentName)
				: "No student selected or student not found";
	}
	@PostMapping("/studentInfo")
	public String setStudentInfo(@RequestParam String name,@RequestParam String age){
//		this.name = name;
// 		this.age = age;
		student.put(name,age);
		selectedStudentName = name;
		return "Student" + name + "added with age" + age;
	}
	@PostMapping("/studentName")
	public void updateStudentName(@RequestParam String name){
		if(student.containsKey(name)){
//			student.put(name,age);
//			this.name = name;
//			this.age = student.get(name);
//			student.put(name,student.get(name));
			selectedStudentName = name;
		}
	}

	@PostMapping("/addStudent")
	public String addStudent(@RequestParam String name, @RequestParam String age){
		student.put(name, age);
		return "Student added" + name + "(" + age + ")";
	}

//	@GetMapping("/name")
//	public String getName(){
//
//		return student.keySet().stream().findFirst().orElse("No student");
//	}
//	@GetMapping("/age")
//	public String getAge(){
//
//		return student.values().stream().findFirst().orElse("No age available");
//	}

	@GetMapping("/allStudents")
	public Map<String,String> getAllStudents(){
		return student;
	}

}
