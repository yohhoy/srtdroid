/*
 * Copyright (C) 2021 Thibault B.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.thibaultbee.srtdroid.models.rejectreason

import io.github.thibaultbee.srtdroid.models.SrtSocket

/**
 * Base class of [InternalRejectReason], [PredefinedRejectReason] and [UserDefinedRejectReason].
 * Do not use it. Its purpose is to get an unique [SrtSocket.rejectReason] API.
 */
abstract class RejectReason