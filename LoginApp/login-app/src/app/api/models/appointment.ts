/* tslint:disable */
import { Patient } from './patient';
export interface Appointment {
  appointmentId?: number;
  appointmentStatus?: 'CANCELLED' | 'CREATED' | 'FINISHED';
  patient?: Patient;
  startDate?: string;
}
