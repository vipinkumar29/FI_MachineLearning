import { FileUploadComponent } from './file-upload/file-upload.component';
import { Routes } from '@angular/router';

export const ROUTES: Routes = [
    {
        path: '', component: FileUploadComponent
    },
    { path: '**', redirectTo: '', pathMatch: 'full' }

];
