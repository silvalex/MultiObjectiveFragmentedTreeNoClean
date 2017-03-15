#!/bin/sh

NUM_RUNS=50

for i in {1..5}; do
  qsub -t 1-$NUM_RUNS:1 fragmented_tree_no_clean.sh ~/workspace/wsc2009/Testset0${i} 2009-fragmented-tree-no-clean${i} nsga2-wsc.params;
done
