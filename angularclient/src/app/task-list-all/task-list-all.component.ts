import { Component, OnInit } from '@angular/core';
import { Task } from '../model/task';
import { TaskService } from '../service/task.service';

@Component({
  selector: 'app-task-list-all',
  templateUrl: './task-list-all.component.html',
  styleUrls: ['./task-list-all.component.css']
})
export class TaskListAllComponent implements OnInit {

  tasks: Task[];

  constructor(private taskService: TaskService) {
  }

  ngOnInit() {
    this.taskService.findAll().subscribe(data => {
      this.tasks = data;
    });
  }
}
