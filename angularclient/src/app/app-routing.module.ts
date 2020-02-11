import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TaskListAllComponent } from './task-list-all/task-list-all.component';
import { TaskFormComponent } from './task-form/task-form.component';
import { TaskDetailComponent } from './task-detail/task-detail.component';

const routes: Routes = [
  { path: 'tasks', component: TaskListAllComponent },
  { path: 'tasks/:uuid',  component: TaskDetailComponent },
  { path: 'addtask', component: TaskFormComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {onSameUrlNavigation: 'reload'})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
