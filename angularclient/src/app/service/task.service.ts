import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Task } from '../model/task';
import { Observable, Subject } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable()
export class TaskService {

  private _refreshNeeded = new Subject<Task>();

  private tasksUrl: string;

  constructor(private http: HttpClient) {
    this.tasksUrl = 'http://localhost:8080/tasks';
  }

  get refreshNeeded() {
    return this._refreshNeeded;
  }

  public findAll(): Observable<Task[]> {
    return this.http.get<Task[]>(this.tasksUrl);
  }

  public find(uuid: string): Observable<Task> {
    return this.http.get<Task>(this.tasksUrl + '/' + uuid)
  }

  public save(task: Task) {
    return this.http.post<Task>(this.tasksUrl, task);
  }

  public addTodo(uuid: string, task: Task) {
    return this.http
      .post<Task>(this.tasksUrl + '/' + uuid + '/todo', task)
      .pipe(
        tap(() =>  {
          this._refreshNeeded.next();
        })
      );
  }
}
