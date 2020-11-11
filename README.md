# ML-PathTraversal

This project presents algorithms being used to build a machine learning system to try to
recognize UCI digits and categorizing them. There are two datasets provided on the
UCI website. The training data which is used to train the system with provided data and
the test data which is used to test this data against the training data.

The data consists of sixty-four values. The first sixty-three are input attributes of
integers consisting of a range from 0 – 16 whereas the last attribute is the class code
which in my report and code will be called the label. There are 10 labels in all with a
range from 0 – 9.

Three algorithms were implemented in total. The Nearest Neighbor Algorithm, the kNearest Neighbor Algorithm and a simplified version of the K-Means Algorithm. These
algorithms will perform a two-fold test and the results for the accuracy will be reported in
this report. This was easily done by just swapping the test data with the training data
when calling from the functions in the code.

See pdf PathTraversal Report in repo for further information on techniques used
