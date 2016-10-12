/*
 * Copyright (C) 2014 Carlos Serramito Calvo <carlossc87@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.carlossc87.todo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import io.github.carlossc87.todo.entities.Task;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Carlos Serramito Calvo <carlossc87@gmail.com>
 */
@Repository
public interface TasksRepository extends JpaRepository<Task, Integer> {
    
}