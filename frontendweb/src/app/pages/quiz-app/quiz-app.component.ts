import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Subscription, interval } from 'rxjs';
@Component({
  selector: 'app-quiz-app',
  templateUrl: './quiz-app.component.html',
  styleUrls: []
})
export class QuizAppComponent implements OnInit {

  showWarning: boolean = false;

  isQuizStarted: boolean = false;
  isQuizEnded: boolean = false;
  questionsList: any[]= [];
  currentQuestionNo: number = 0;

  remainingTime:number = 10;

  timer = interval(1000);
  subscription: Subscription [] = [];
  correctAnswerCount: number = 0;

  departments: any[]=[];
 
  deptObj: any = {
    DeptId: 0,
    DepartmentName: ''
  }
  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.loadDepartmen();
  }

  loadDepartmen() {
    this.http.get("http://localhost:61334/api/master/GetDepartment").subscribe((res:any)=> {
     this.departments = res;
    })
  }

  saveDepartment() { 
    this.http.post("http://localhost:61334/api/master/addDept", this.deptObj).subscribe(res=> {
      debugger;
      this.loadDepartmen();
    })
  }
  updateDepartment() {
    this.http.post("http://localhost:61334/api/Department/updateDepartment" ,this.deptObj).subscribe(res=> {
      debugger;
      this.loadDepartmen();
    })
  }
  ondelete(id: number) {
    this.http.get("http://localhost:61334/api/Department/deletDeptById?id="+ id).subscribe(res=> {
      debugger;
      this.loadDepartmen();
    })
  }
  onEdit(obj: any) {
    this.deptObj = obj;
  }


  // ngOnInit(): void { 
  //   this.loadQuestions();
  // }
  // loadQuestions() {
  //   this.http.get("assets/questions.json").subscribe((res:any)=>{
  //     debugger;
  //     this.questionsList = res;
  //   })
  // }
  // nextQuestion() {
  //   if(this.currentQuestionNo < this.questionsList.length-1) {
  //     this.currentQuestionNo ++;
  //   } else {
  //     this.subscription.forEach(element => {
  //       element.unsubscribe();
  //     });
  //   } 
  // }
  // finish() {
  //   this.isQuizEnded = true;
  //   this.isQuizStarted = false;  
  // }

  // start() {
  //   this.showWarning = false;
  //   this.isQuizEnded = false;
  //   this.isQuizStarted = false;  
  // }

  // showWarningPopup() {
  //   this.showWarning = true;
  // }

  // selectOption(option: any) {
  //   if(option.isCorrect) {
  //     this.correctAnswerCount ++;
  //   }
  //   option.isSelected = true;
  // }
  // isOptionSelected(options: any) {
  //   const selectionCount = options.filter((m:any)=>m.isSelected == true).length;
  //   if(selectionCount == 0) {
  //     return false;
  //   } else {
  //     return true;
  //   }
  // }
  // startQuiz() {
  //   this.showWarning = false;
  //   this.isQuizStarted = true;  
  //  this.subscription.push(this.timer.subscribe(res=> {
  //     console.log(res);
  //     if(this.remainingTime != 0) {
  //       this.remainingTime --;
  //     } 
  //     if(this.remainingTime == 0) {
  //       this.nextQuestion();
  //       this.remainingTime = 10;
  //     } 
  //   })
  //  )
  // }
}
