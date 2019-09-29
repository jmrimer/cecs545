import React from 'react';
import styled from 'styled-components';
import classNames from 'classnames';
import { StyledMapInput } from '../shared-graphic-components/MapInput';
import { TrialModel } from './TrialModel';
import MultiPathVisualGraph from './MultiPathVisualGraph';

interface Props {
  trial: TrialModel;
  currentGeneration: number;
  loading: boolean;
  getNewTrial: (mapText: string) => void;
  updateMapText: (e: any) => void;
  mapText: string;
  points: any[];
  nextGeneration: () => void;
  className?: string;
}

export const TrialDisplayer: React.FC<Props> = props => {
  let {
    currentGeneration,
    loading,
    getNewTrial,
    updateMapText,
    mapText,
    trial,
    points,
    className
  } = props;

  function renderMapInput() {
    return <div className={'input'}>
      <div className={'title'}>INPUT</div>
      <StyledMapInput
        getNewRoute={getNewTrial}
        updateMapText={updateMapText}
        mapText={mapText}
      />
    </div>
  }

  function renderTrialOutput() {
    function filterBestChildRoutes() {
      let generation = trial.generations[currentGeneration];
      if (generation) {
        let children = generation.children;
        children.sort((a, b) => a.weight < b.weight ? -1 : a.weight > b.weight ? 1 : 0);
        children = children.filter((child, index) => {
          return index < 10;
        });
        return children;
      }
      return null;
    }

    return (
      <div className={'output'}>
        {
          loading ?
            <div>Loading...</div> :
            <div/>
        }
        <div className={'graph-box'}>
          <MultiPathVisualGraph
            className={'parents'}
            points={points}
            routes={trial.generations[currentGeneration] ? trial.generations[currentGeneration].parents : null}
          />
          <MultiPathVisualGraph
            className={'children'}
            points={points}
            routes={filterBestChildRoutes()}
          />
        </div>
        <button className={'next-generation'} onClick={() => props.nextGeneration()}>Next Gen</button>
      </div>
    )
  }

  return (
    <div className={classNames('trial-displayer', className)}>
      {renderMapInput()}
      {renderTrialOutput()}
    </div>
  )
};

export default styled(TrialDisplayer)`
  display: flex;
  flex-direction: row;

  .output {
    display: flex;
    flex-direction: column;
  }
  
  .graph-box {
    display: flex;
    flex-direction: row;
  }
`;