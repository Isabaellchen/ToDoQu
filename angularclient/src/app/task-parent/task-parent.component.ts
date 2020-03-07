import { Component, Input } from '@angular/core';
import { Task } from '../model/task';

@Component({
  selector: 'app-task-parent',
  templateUrl: './task-parent.component.html',
  styleUrls: ['./task-parent.component.css']
})
export class TaskParentComponent {

  @Input() parent: Task;

}
