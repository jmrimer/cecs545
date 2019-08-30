import { shallow, ShallowWrapper } from 'enzyme';
import React from 'react';
import { RouteContainer } from './RouteContainer';
import { RouteInfo } from './RouteInfo';
import { RouteModel } from '../models/RouteModel';
import { MapInput } from './MapInput';

describe('RouteContainer', () => {
  let routeContainer: ShallowWrapper;
  let weightedRoute: RouteModel;

  beforeEach(() => {
    weightedRoute = new RouteModel();

    routeContainer = shallow(
      <RouteContainer
        getRoute={() => {
        }}
        weightedRoute={weightedRoute}
        loading={false}/>
    );
  });

  it('should have a div', () => {
    expect(routeContainer.find('div').exists()).toBeTruthy();
  });

  it('should setup and display weightedRoute info', () => {
    expect(routeContainer.find(RouteInfo).props().weightedRoute).toBe(weightedRoute);
    expect(routeContainer.find(RouteInfo).exists()).toBeTruthy();
  });

  it('should display loading info', () => {
    expect(routeContainer.find('.loading').exists()).toBeFalsy();
    routeContainer = shallow(
      <RouteContainer
        getRoute={() => {
        }}
        weightedRoute={weightedRoute}
        loading={true}
      />
    );
    expect(routeContainer.find('.loading').exists()).toBeTruthy();
  });

  it('should display and facilitate map input', () => {
    expect(routeContainer.find(MapInput).exists()).toBeTruthy();
  });

  it('should provide a button to calculate route from input', () => {
    expect(routeContainer.find('button').exists()).toBeTruthy();
  });
});