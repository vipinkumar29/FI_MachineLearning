import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { Http, Response, URLSearchParams, RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';

@Injectable()
export class FileUploadService {

    private _decisionTreeURL = environment.decisionTree;
    private _logicalRegressionURL = environment.logicalRegression;
    private _naiveBayesURL = environment.naiveBayes;
    private _kNearestNeighbourURL = environment.kNearestNeighbour;
    private _linearRegressionURL = environment.linearRegression;
    private _randomForestURL = environment.randomForest;
    private _artificialNeuralNetworksURL = environment.artificialNeuralNetworks;
    private _kMeansClustAlgoURL = environment.kMeansClustAlgo;
    private _aprioriURL = environment.apriori;
    private _svmURL = environment.supportVectorMachine;

    constructor(private _http: Http) { }

    public uploadAlgoFile(fileType: string, files: File): Observable<any> {
        console.log('Service Called');
        const formData: FormData = new FormData();
        formData.append('file', files);
        const head = new Headers();
        head.append('Accept', 'application/json');
        const options = new RequestOptions({ headers: head });
        let url;
        if (fileType === 'Decision Tree') {
            url = this._decisionTreeURL;
        } else if (fileType === 'Logical Regression') {
            url = this._logicalRegressionURL;
        } else if (fileType === 'Naive Bayes') {
            url = this._naiveBayesURL;
        } else if (fileType === 'Kth Nearest Neighbour') {
            url = this._kNearestNeighbourURL;
        } else if (fileType === 'Linear Regression') {
            url = this._linearRegressionURL;
        } else if (fileType === 'Random Forest') {
            url = this._randomForestURL;
        } else if (fileType === 'K Means Clustering Algorithm') {
            url = this._kMeansClustAlgoURL;
        } else if (fileType === 'Artificial Neural Networks') {
            url = this._artificialNeuralNetworksURL;
        } else if (fileType === 'Apriori') {
            url = this._aprioriURL;
        } else if (fileType === 'Support Vector Machine') {
            url = this._svmURL;
        }

        if (environment.production) {
            if (fileType === 'Random Forest') {
                return this._http.get(url)
                    .map((response: Response) => <any[]>response.json())
                    .do(response => console.log(response))
                    .catch(this.handleError);
            } else {
                return this._http.post(url, formData, options)
                    .map((response: Response) => <any[]>response.json())
                    .do(response => console.log(response))
                    .catch(this.handleError);
            }

        } else {
            return this._http.get(url)
                .map((response) => response.json())
                .do(response => console.log(response))
                .catch(this.handleError);
        }
    }

    private handleError(error: Response) {
        return Observable.throw(error.json().error || 'Server error');
    }
}
