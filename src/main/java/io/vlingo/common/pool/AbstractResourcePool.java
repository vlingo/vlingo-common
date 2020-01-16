// Copyright © 2012-2020 VLINGO LABS. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.

package io.vlingo.common.pool;

/**
 * An abstract {@link ResourcePool} that implements {@link ResourcePool#acquire()}
 * using the default arguments from {@link ResourceFactory#defaultArguments()}.
 *
 * @param <Resource>  the type of the pooled resource
 * @param <Arguments> the type of the arguments to the acquire method
 */
abstract class AbstractResourcePool<Resource, Arguments> implements ResourcePool<Resource, Arguments> {

  final ResourceFactory<Resource, Arguments> factory;

  AbstractResourcePool(ResourceFactory<Resource, Arguments> factory) {
    this.factory = factory;
  }

  /**
   * Uses {@link ResourceFactory#defaultArguments()} to {@link ResourcePool#acquire(Object)} a resource object.
   *
   * @return a resource object with the default arguments
   * @see ResourceFactory#defaultArguments()
   */
  @Override
  public Resource acquire() {
    return acquire(factory.defaultArguments());
  }

  /**
   * Uses {@link ResourceFactory#defaultArguments()} to {@link ResourcePool#borrow(Object)} a {@link ResourceLease}.
   *
   * @return a ResourceLease with resource object acquired with the default arguments
   * @see ResourceFactory#defaultArguments()
   */
  @Override
  public ResourceLease<Resource> borrow() {
    return borrow(factory.defaultArguments());
  }
}
