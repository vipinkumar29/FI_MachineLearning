// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const environment = {
  production: false,
  decisionTree: 'assets/DT.json',
  logicalRegression: 'assets/LogReg.json',
  naiveBayes: 'assets/nb.json',
  kNearestNeighbour: 'assets/KNN.json',
  linearRegression: 'assets/LinReg.json',
  randomForest: 'assets/RF.JSON',
  artificialNeuralNetworks: 'assets/ANN.JSON',
  kMeansClustAlgo: 'assets/KMeansClust.json',
  apriori: 'assets/apriori.json',
  supportVectorMachine: 'assets/svm.json'
};
