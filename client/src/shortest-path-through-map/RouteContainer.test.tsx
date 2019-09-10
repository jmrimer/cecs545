import { shallow, ShallowWrapper } from 'enzyme';
import React from 'react';
import { RouteContainer } from './RouteContainer';
import { StyledRouteInfo } from './RouteInfo';
import { RouteModel } from './models/RouteModel';
import { StyledMapInput } from './MapInput';
import { VisualGraph } from '../visual-grapher/VisualGraph';

describe('RouteContainer', () => {
  let routeContainer: ShallowWrapper;
  let weightedRoute: RouteModel;
  let mapText = 'map text';
  let getNewRoute = () => {
    return null;
  };
  let updateMapText = () => {
    return null;

  };
  beforeEach(() => {

    weightedRoute = new RouteModel();
    routeContainer = shallow(
      <RouteContainer
        getStaticRoute={() => {
        }}
        getNewRoute={getNewRoute}
        updateMapText={updateMapText}
        mapText={mapText}
        weightedRoute={weightedRoute}
        loading={false}
        points={['point1', 'point2']}
      />
    );

  });

  it('should have a div', () => {
    expect(routeContainer.find('div').exists()).toBeTruthy();
  });

  it('should setup and display weightedRoute info', () => {
    expect(routeContainer.find(StyledRouteInfo).props().weightedRoute).toBe(weightedRoute);
    expect(routeContainer.find(StyledRouteInfo).exists()).toBeTruthy();
  });

  it('should display and facilitate map input', () => {
    expect(routeContainer.find(StyledMapInput).exists()).toBeTruthy();
    expect(routeContainer.find(StyledMapInput).prop('getNewRoute')).toBe(getNewRoute);
    expect(routeContainer.find(StyledMapInput).prop('updateMapText')).toBe(updateMapText);
    expect(routeContainer.find(StyledMapInput).prop('mapText')).toBe(mapText);
  });

  it('should display a graph and provide points & maps to it', () => {
    expect(routeContainer.find(VisualGraph).exists()).toBeTruthy();
    let nodesAndEdges: any[] = [];
    expect(routeContainer.find(VisualGraph).prop('points')).toEqual(['point1', 'point2']);
    expect(routeContainer.find(VisualGraph).prop('tour')).toEqual(weightedRoute.route);
  });
});
