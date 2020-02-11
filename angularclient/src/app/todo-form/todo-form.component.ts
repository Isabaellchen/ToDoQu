import { Component, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TaskService } from '../service/task.service';
import { Task } from '../model/task';

@Component({
  selector: 'app-todo-form',
  templateUrl: './todo-form.component.html',
  styleUrls: ['./todo-form.component.css']
})
export class TodoFormComponent {

  @Input() parent: Task;
  task: Task;

    constructor(
      private route: ActivatedRoute,
      private router: Router,
      private taskService: TaskService
    ) {
        this.task = new Task();
    }

    onSubmit() {
      this.taskService.addTodo(this.parent.uuid, this.task).subscribe(result => this.gotoTaskList());
    }

    gotoTaskList() {
      this.router.navigate(['/tasks', this.parent.uuid]);
    }

}
