import React from 'react';
import classNames from 'classnames';
import { StyledRouteInfo } from '../brute-force/RouteInfo';
import { StyledMapInput } from '../brute-force/MapInput';
import VisualGraph from '../visual-grapher/VisualGraph';
import { RouteModel } from '../brute-force/models/RouteModel';
import styled from 'styled-components';

interface Props {
  weightedRoute: RouteModel;
  loading: boolean;
  mapText: string;
  getNewRoute: (mapText: string) => void;
  updateMapText: (e: any) => void;
  points: any[];
  className?: string;
}

export const TourDisplayer: React.FC<Props> = props => {
  let {
    weightedRoute,
    loading,
    mapText,
    getNewRoute,
    updateMapText,
    points,
    className
  } = props;

  function renderRouteOutput() {
    return <div className={'output'}>
      OUTPUT
      <StyledRouteInfo
        weightedRoute={weightedRoute}
        loading={loading}
      />
      {renderMap()}
    </div>;
  }

  function renderMapInput() {
    return <div className={'input'}>
      INPUT
      <StyledMapInput
        getNewRoute={getNewRoute}
        updateMapText={updateMapText}
        mapText={mapText}
      />
    </div>
  }

  function renderDividingLine() {
    return <div className={'divide'}>&nbsp;</div>
  }

  function renderMap() {
    return (
      <VisualGraph
        points={points}
        tour={weightedRoute ? weightedRoute.route : null}
      />
    );
  }

  return (
    <div className={classNames('tour-displayer', className)}>
      {renderMapInput()}
      {renderDividingLine()}
      {renderRouteOutput()}
    </div>
  );
};

export default (styled(TourDisplayer)`
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  margin-top: 24px;
  
  .input, .output {
    font-family: Righteous, cursive;
    font-size: 36px;
    color: ${(props) => props.theme.color.fontWhite};
  }
  
  .divide {
    width: 2px;
    border: 2px solid ${(props) => props.theme.color.fontWhite};
    margin: 8px 16px;
  }
`);

