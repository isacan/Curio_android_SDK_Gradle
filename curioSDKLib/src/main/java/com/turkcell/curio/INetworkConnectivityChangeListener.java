/*
 * Copyright (C) 2014 Turkcell
 * 
 * Created by Can Ciloglu on 16 Haz 2014
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.turkcell.curio;

/**
 * Listener interface for network connectivity change events. 
 * 
 * @author Can Ciloglu
 *
 */
public interface INetworkConnectivityChangeListener {
	
	/**
	 * Notifies network connectivity changes.
	 * 
	 * @param isConnected
	 */
	public void networkConnectivityChanged(boolean isConnected);
}
