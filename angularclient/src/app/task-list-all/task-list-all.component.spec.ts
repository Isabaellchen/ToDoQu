import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TaskListAllComponent } from './task-list-all.component';

describe('TaskListAllComponent', () => {
  let component: TaskListAllComponent;
  let fixture: ComponentFixture<TaskListAllComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TaskListAllComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TaskListAllComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
