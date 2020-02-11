export class Task {
  uuid: string;
  title: string;
  description: string;
  procrastinateUntil: Date;
  dueDate: Date;

  parent: Task;
  tasks: Task[];
}
