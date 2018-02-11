import { Component, OnInit, ViewEncapsulation, Renderer } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators, ValidatorFn, AbstractControl } from '@angular/forms';
import { FileUploadService } from './file-upload.service';
import { Subscription } from 'rxjs/Subscription';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class FileUploadComponent implements OnInit {

  public fileUploadForm: FormGroup;
  public fileTypes = ['Decision Tree', 'Naive Bayes', 'Logical Regression', 'Kth Nearest Neighbour', 'Linear Regression', 'Random Forest', 'K Means Clustering Algorithm', 'Artificial Neural Networks', 'Apriori', 'Support Vector Machine'];
  private _file: File;
  private _files: any[];
  private _selectedFileName = '';
  private _rowData: any[];
  private _flagLR = false;
  private _flagDT = false;
  private _flagNB = false;
  private _flagKNN = false;
  private _flagLinReg = false;
  private _flagRanFor = false;
  private _kMeansClustAlgo = false;
  private _flagANN = false;
  private _flagApriori = false;
  private _flagsvm = false;

  constructor(private _renderer: Renderer, private _fb: FormBuilder, private _fileUploadService: FileUploadService) { }

  ngOnInit() {
    this.fileUploadForm = this._fb.group({
      fileType: [''],
      files: ['']
    });
  }

  fileTypeChange() {
    console.log('file Type change');
    this._file = null;
    this.fileUploadForm.controls['files'].setValue(null);
  }

  fileChange(event) {
    const fileList: FileList = event.target.files;
    if (fileList.length > 0) {
      this._selectedFileName = fileList[0].name;
      this._file = fileList[0];
    }
  }

  onUpload() {
    const fileType = this.fileUploadForm.controls['fileType'].value;
    console.log(fileType);
    console.log(this._file);


    this._fileUploadService.uploadAlgoFile(fileType, this._file).subscribe(response => {
      this._rowData = response;
      if (fileType === 'Decision Tree') {
        this._flagLR = false;
        this._flagNB = false;
        this._flagDT = true;
        this._flagKNN = false;
        this._flagLinReg = false;
        this._flagRanFor = false;
        this._kMeansClustAlgo = false;
        this._flagANN = false;
        this._flagApriori = false;
        this._flagsvm = false;
      } else if (fileType === 'Logical Regression') {
        this._flagDT = false;
        this._flagNB = false;
        this._flagLR = true;
        this._flagKNN = false;
        this._flagLinReg = false;
        this._flagRanFor = false;
        this._kMeansClustAlgo = false;
        this._flagANN = false;
        this._flagApriori = false;
        this._flagsvm = false;
      } else if (fileType === 'Naive Bayes') {
        this._flagDT = false;
        this._flagLR = false;
        this._flagNB = true;
        this._flagKNN = false;
        this._flagLinReg = false;
        this._flagRanFor = false;
        this._kMeansClustAlgo = false;
        this._flagANN = false;
        this._flagApriori = false;
        this._flagsvm = false;
      } else if (fileType === 'Kth Nearest Neighbour') {
        this._flagDT = false;
        this._flagLR = false;
        this._flagNB = false;
        this._flagKNN = true;
        this._flagLinReg = false;
        this._flagRanFor = false;
        this._kMeansClustAlgo = false;
        this._flagANN = false;
        this._flagApriori = false;
        this._flagsvm = false;
      } else if (fileType === 'Linear Regression') {
        this._flagDT = false;
        this._flagLR = false;
        this._flagNB = false;
        this._flagKNN = false;
        this._flagLinReg = true;
        this._flagRanFor = false;
        this._kMeansClustAlgo = false;
        this._flagANN = false;
        this._flagApriori = false;
        this._flagsvm = false;
      } else if (fileType === 'Random Forest') {
        this._flagDT = false;
        this._flagLR = false;
        this._flagNB = false;
        this._flagKNN = false;
        this._flagLinReg = false;
        this._flagRanFor = true;
        this._kMeansClustAlgo = false;
        this._flagANN = false;
        this._flagApriori = false;
        this._flagsvm = false;
      } else if (fileType === 'K Means Clustering Algorithm') {
        this._flagDT = false;
        this._flagLR = false;
        this._flagNB = false;
        this._flagKNN = false;
        this._flagLinReg = false;
        this._flagRanFor = false;
        this._kMeansClustAlgo = true;
        this._flagANN = false;
        this._flagApriori = false;
        this._flagsvm = false;
      } else if (fileType === 'Artificial Neural Networks') {
        this._flagDT = false;
        this._flagLR = false;
        this._flagNB = false;
        this._flagKNN = false;
        this._flagLinReg = false;
        this._flagRanFor = false;
        this._kMeansClustAlgo = false;
        this._flagANN = true;
        this._flagApriori = false;
        this._flagsvm = false;
      } else if (fileType === 'Apriori') {
        this._flagDT = false;
        this._flagLR = false;
        this._flagNB = false;
        this._flagKNN = false;
        this._flagLinReg = false;
        this._flagRanFor = false;
        this._kMeansClustAlgo = false;
        this._flagANN = false;
        this._flagApriori = true;
        this._flagsvm = false;
      } else if (fileType === 'Support Vector Machine') {
        this._flagDT = false;
        this._flagLR = false;
        this._flagNB = false;
        this._flagKNN = false;
        this._flagLinReg = false;
        this._flagRanFor = false;
        this._kMeansClustAlgo = false;
        this._flagANN = false;
        this._flagApriori = false;
        this._flagsvm = true;
      }
      console.log(this._rowData);
    });
  }

}
