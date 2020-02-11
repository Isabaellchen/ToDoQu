import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Subscription } from 'rxjs';
import 'rxjs/Rx';
import { TaskListAllComponent } from '../task-list-all/task-list-all.component';
import { Task } from '../model/task';
import { TaskService } from '../service/task.service';

@Component({
  selector: 'app-task-detail',
  templateUrl: './task-detail.component.html',
  styleUrls: ['./task-detail.component.css']
})
export class TaskDetailComponent implements OnInit, OnDestroy {

  task: Task;
  routerParams: Subscription;


  constructor(
    private taskService: TaskService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.taskService.refreshNeeded.subscribe(() => {
      this.doRefresh();
    })

    this.doRefresh();
  }

  private doRefresh() {
    this.routerParams = this.route.params.subscribe(params => {
      this.taskService.find(params['uuid']).subscribe(data => {
        this.task = data;
      });
    });
  }

  ngOnDestroy() {
    this.routerParams.unsubscribe();
  }

}
